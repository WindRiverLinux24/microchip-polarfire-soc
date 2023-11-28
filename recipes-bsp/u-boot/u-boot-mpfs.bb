require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"


FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PV = "2023.06+git${SRCPV}"
# tag: linux4microchip+fpga-2023.06
SRCREV = "7e19f9dff788025403ac6a34d9acf8736eef32ff"
SRC_URI = "git://github.com/polarfire-soc/u-boot.git;protocol=https;nobranch=1  \
           file://${UBOOT_ENV}.txt \
           file://${HSS_PAYLOAD}.yaml \
           file://0001-riscv-Fix-build-against-binutils-2.38.patch \
	   file://bootargs.patch \
	   file://cfgsaveenv.patch \
          "

DEPENDS:append = " u-boot-tools-native hss-payload-generator-native"

# Overwrite this for your server
TFTP_SERVER_IP ?= "127.0.0.1"

do_configure:prepend () {

    if [ -f "${WORKDIR}/${UBOOT_ENV}.txt" ]; then
        cp ${WORKDIR}/${UBOOT_ENV}.txt ${WORKDIR}/${UBOOT_ENV}.txt.pp
        sed -i -e 's,@SERVERIP@,${TFTP_SERVER_IP},g' ${WORKDIR}/${UBOOT_ENV}.txt.pp
        sed -i -e 's/@MTDPARTS@/${MPFS_MTDPARTS}/gI' ${WORKDIR}/${UBOOT_ENV}.txt.pp
        sed -i -e 's/@MTDTYPE@/${MPFS_MTD_TYPE}/gI' ${WORKDIR}/${UBOOT_ENV}.txt.pp
        mkimage -O linux -T script -C none -n "U-Boot boot script" \
            -d ${WORKDIR}/${UBOOT_ENV}.txt.pp ${WORKDIR}/boot.scr.uimg
    fi
}

do_install:append() {
    if [ -f "${WORKDIR}/${UBOOT_ENV}.txt" ]; then
        install -m 644 ${WORKDIR}/${UBOOT_ENV_BINARY}.pp ${D}/boot/${UBOOT_ENV_IMAGE}
        ln -sf ${UBOOT_ENV_IMAGE} ${D}/boot/${UBOOT_ENV_BINARY}
    fi
}

do_deploy:append () {
    if [ -f "${WORKDIR}/boot.scr.uimg" ]; then
       rm -f ${DEPLOYDIR}/boot.scr.uimg
       install -m 755 ${WORKDIR}/boot.scr.uimg ${DEPLOYDIR}
    fi

    hss-payload-generator -c ${WORKDIR}/${HSS_PAYLOAD}.yaml -v ${DEPLOYDIR}/payload.bin
}

FILES:${PN}:append:microchip-polarfire-soc = " /boot/boot.scr.uimg"

COMPATIBLE_MACHINE = "(microchip-polarfire-soc)"

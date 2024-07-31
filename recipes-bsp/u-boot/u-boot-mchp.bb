require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc
require u-boot-env-mpfs.inc

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"


FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PV = "v2023.07-mchp+git${SRCPV}"
SRCREV = "linux4microchip+fpga-2024.06"
SRC_URI = "git://github.com/linux4microchip/u-boot-mchp.git;protocol=https;nobranch=1  \
           file://${HSS_PAYLOAD}.yaml \
	   file://boot.cmd \
           file://mpfsoc.cfg \
           file://uEnv.txt \
          "

DEPENDS:append = " u-boot-tools-native hss-payload-generator-native"
DEPENDS += "coreutils-native"
DEPENDS += " python3-setuptools-native u-boot-mkenvimage-native"


do_deploy:append () {

    hss-payload-generator -c ${WORKDIR}/${HSS_PAYLOAD}.yaml -v ${DEPLOYDIR}/payload.bin

}

COMPATIBLE_MACHINE = "(microchip-polarfire-soc)"

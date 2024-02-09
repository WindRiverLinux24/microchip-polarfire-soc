SUMMARY = "Driver module providing user space DMA access"
DESCRIPTION = "${SUMMARY}"
SECTION = "kernel"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=bebf0492502927bef0741aa04d1f35f5"

DEPENDS = "virtual/kernel elfutils"

inherit module

SRC_URI = "git://github.com/ikwzm/udmabuf.git;protocol=https;branch=master"

# Master branch - driver version = 4.5.2
SRCREV = "d45c465c270e276ad30284f8b34fa377b1deccb3"

S = "${WORKDIR}/git"

MODULE_NAME = "u-dma-buf"

do_install() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/${MODULE_NAME}
    install -D -m 0644 "${S}/u-dma-buf.ko" "${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/${MODULE_NAME}"
}

COMPATIBLE_HOST = "(riscv|riscv64).*-linux"
KERNEL_MODULE_AUTOLOAD:append:microchip-polarfire-soc = "u-dma-buf"
COMPATIBLE_MACHINE:microchip-polarfire-soc = "microchip-polarfire-soc"

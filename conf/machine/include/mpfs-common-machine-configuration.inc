#@TYPE: Machine Include
#@SOC: Polarfire SoC
#@DESCRIPTION: Common Machine Configuration for Polarfire SoC

require conf/machine/include/riscv/tune-riscv.inc

MACHINE_FEATURES = "pci usbgadget ext2 ext3 serial"

KERNEL_CLASSES = "kernel-fitimage"
KERNEL_IMAGETYPE = "fitImage"
EXTERNAL_KERNEL_DEVICETREE = "${DEPLOY_DIR_IMAGE}/overlays"

RISCV_SBI_PLAT = "generic"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot-mchp"
PREFERRED_PROVIDER_u-boot = "u-boot-mchp"

UBOOT_ENTRYPOINT = "0x80200000"
UBOOT_DTB_LOADADDRESS = "0x8a000000"
UBOOT_DTBO_LOADADDRESS = "0x8a080000"
UBOOT_RD_LOADADDRESS = "0x8aa00000"
UBOOT_RD_ENTRYPOINT = "0x8aa00000"

MACHINE_EXTRA_RRECOMMENDS += " kernel-modules"

IMAGE_FSTYPES += "wic wic.gz wic.bmap ext4"

IMAGE_INSTALL:append = " dt-overlay-mchp"

## wic default support
WKS_FILE_DEPENDS ?= " \
    e2fsprogs-native \
    bmaptool-native \
    u-boot-mchp \
"

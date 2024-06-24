require linux-yocto-mpfsoc-icicle.inc

KBRANCH:microchip-polarfire-soc  = "v6.1/standard/preempt-rt/microchip-polarfire-soc"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:microchip-polarfire-soc = " file://0001-riscv-Allow-to-enable-RT.patch"

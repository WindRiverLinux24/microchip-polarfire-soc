#
# Copyright (C) 2023 Wind River Systems, Inc.
#

TARGET_SUPPORTED_KTYPES:microchip-polarfire-soc = "standard preempt-rt"
COMPATIBLE_MACHINE:microchip-polarfire-soc = "microchip-polarfire-soc"

inherit siteinfo
KERNEL_DTC_FLAGS:append:microchip-polarfire-soc = " -@ -H epapr"

WATCHDOG_MODULE ?= "softdog"

do_install:append:microchip-polarfire-soc() {
    if [ -n "${WATCHDOG_MODULE}" ]; then
        echo "watchdog_module = \"${WATCHDOG_MODULE}\"" >> ${D}/etc/default/watchdog
    fi
}

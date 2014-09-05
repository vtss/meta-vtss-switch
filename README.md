meta-vtss-switch
================

Yocto switch application layer for Vitesse MIPS-based switch products.

This Yocto layer supports building the "vtss-core-minimal" flash
image, which contain the Vitesse Unified Switch API for the supported
platforms.

NOTE: You will need to obtain the Switch application sources
separately. Refer to recipes-applications/vtss-api/files/README for
more information.

The supported machine targets are:

MACHINE         | Target board(s)
-------         | ---------------
luton10         | VSC7424EV, VSC7428EV
luton26         | VSC5610EV, VSC5611EV
jaguar1-cu24    | VSC5606EV
jaguar1-cu48    | VSC5608EV
serval1         | VSC5616EV, VSC5617EV, VSC5618EV, VSC5619EV
serval2         | VSC5629EV
jaguar2-cu24    | VSC5628EV
jaguar2-cu48    | VSC5627EV

You will also need the BSP layer, located at
http://github.com/vtss/meta-vtss-vcoreiii.

The options used to compile the VTSS switch API is derived from the
`MACHINE` setting of the Yocto build. To see the options being used,
you can execute the following command:

> bitbake -e vtss-api | grep VTSS_API_DEFINES

If desired, you can override `VTSS_API_DEFINES` by adding a lines like
the ones below to your `local.conf` Yocto build configuration file:

> VTSS_API_DEFINES_luton10 = "-DVTSS_PRODUCT_CHIP=SERVAL_1 -DVTSS_OPT_PORT_COUNT=8 -DVTSS_PRODUCT_HW=BOARD_LUTON10_REF -DVTSS_CHIP_10G_PHY=OFF"

The example above change the API compile flags to use the
Carrier-Ethernet chip variant `SERVAL_1`, and also change the port
count of the target device.

It is recommended to suffix the builds by the machine target - in case
you need to support multiple target systems.

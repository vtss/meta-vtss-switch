meta-vtss-switch
================

Yocto switch application layer for Vitesse MIPS-based switch products.

This Yocto layer supports building the "vtss-core-minimal" flash image, which
contain the Vitesse Unified Switch API for the supported platforms.

NOTE: You will need to obtain the Switch application sources separately. Refer
to recipes-applications/vtss-api/files/README for more information.

The supported machine targets are:

*   jaguar1
*   serval1
*   luton26 (partially)

You will also need the BSP layer, located at http://github.com/vtss/meta-vtss-vcoreiii.

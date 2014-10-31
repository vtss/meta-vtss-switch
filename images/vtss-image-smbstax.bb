require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL += " vtss-api mtd-utils tcpdump dropbear"

IMAGE_INSTALL_append_serval1 = " smbstax hiawatha"

IMAGE_INSTALL_append_serval2 = " smbstax hiawatha"

LICENSE = "MIT"

IMAGE_FSTYPES ?= "squashfs-xz"

IMAGE_DEPENDS_squashfs-xz = "squashfs-tools-native"

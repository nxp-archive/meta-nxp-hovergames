require imx-image-hovergames.bb

SUMMARY = "Hovergames minimal image"
DESCRIPTION = "A minimal image for Hovergames. Removes build tools and \
weston desktop from the standard Hovergames image."

IMAGE_FEATURES_remove = " \
    dbg-pkgs \
    dev-pkgs \
    tools-sdk \
    tools-profile \
"

IMAGE_INSTALL_remove = " \
    weston \
    weston-init \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)} \
"

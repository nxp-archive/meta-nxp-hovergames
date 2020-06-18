require imx-image-hovergames.bb

SUMMARY = "HoverGames minimal image"
DESCRIPTION = "A minimal image for HoverGames. Removes build tools and \
weston desktop from the standard HoverGames image."

IMAGE_FEATURES_remove = " \
    dbg-pkgs \
    dev-pkgs \
    tools-sdk \
    tools-profile \
"

IMAGE_INSTALL_remove = " \
    cmake \
    weston \
    weston-init \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)} \
"

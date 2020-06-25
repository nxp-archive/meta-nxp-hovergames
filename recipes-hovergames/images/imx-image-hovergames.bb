SUMMARY = "TBD"
DESCRIPTION = "TBD"

IMAGE_FEATURES += " \
    splash \
    package-management \
    ssh-server-dropbear \
    dbg-pkgs \
    debug-tweaks \
    dev-pkgs \
    tools-sdk \
    tools-profile \
"

LICENSE = "MIT"

inherit core-image distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

CORE_IMAGE_BASE_INSTALL += " \
    opencv \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
    weston \
    weston-init \
"
CORE_IMAGE_BASE_INSTALL += \
    "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

require recipes-core/images/ros-image-core.bb

SUMMARY = "Hovergames image"
DESCRIPTION = "An image for Hovergames, including core ROS plus MAVROS, \
OpenCV, and gstreamer, plus build tools and weston desktop"

IMAGE_FEATURES += " \
    dbg-pkgs \
    debug-tweaks \
    dev-pkgs \
    package-management \
    splash \
    ssh-server-dropbear \
    tools-sdk \
    tools-profile \
"

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_INSTALL_append = " \
    mavros \
    mavros-msgs \
    opencv \
    opencv-apps \
    opencv-samples \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
    python-opencv \
    weston \
    weston-init \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)} \
"

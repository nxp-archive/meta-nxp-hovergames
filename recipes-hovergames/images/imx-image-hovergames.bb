require recipes-core/images/ros-image-core.bb

SUMMARY = "HoverGames image"
DESCRIPTION = "An image for HoverGames, including core ROS plus MAVROS, \
OpenCV, and gstreamer, plus build tools and weston desktop"

IMAGE_FEATURES += " \
    dbg-pkgs \
    debug-tweaks \
    dev-pkgs \
    package-management \
    splash \
    ssh-server-openssh \
    tools-sdk \
    tools-profile \
"

inherit distro_features_check

REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_INSTALL_append = " \
    ca-certificates \
    cmake \
    connman-tools \
    connman-tests \
    connman-client \
    gnupg \
    mavlink-router \
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

# meta-freescale code to include QCA 9377 support is not working
IMAGE_INSTALL_append = " \
    firmware-qca9377 \
    kernel-module-qca9377 \
    qca-tools \
"
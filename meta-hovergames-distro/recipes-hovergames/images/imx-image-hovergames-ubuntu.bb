# A NavQ image with an Ubuntu rootfs
#
# Note that we have a tight dependency to ubuntu-base
# and that we cannot just install arbitrary Yocto packages to avoid
# rootfs pollution or destruction.
PV = "${@d.getVar('PREFERRED_VERSION_ubuntu-base', True) or '1.0'}"

IMAGE_LINGUAS = ""
IMAGE_INSTALL = ""
inherit core-image image nativeaptinstall distro_features_check
export PACKAGE_INSTALL = "${IMAGE_INSTALL}"

APTGET_CHROOT_DIR = "${IMAGE_ROOTFS}"
APTGET_SKIP_UPGRADE = "1"

ROOTFS_POSTPROCESS_COMMAND_append = "do_aptget_update; do_update_host; do_update_dns; do_enable_network_manager;"

REQUIRED_DISTRO_FEATURES = "wayland"
CORE_IMAGE_BASE_INSTALL += "weston weston-init"
CORE_IMAGE_BASE_INSTALL += \
    "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

# This must be added first as it provides the foundation for
# subsequent modifications to the rootfs
IMAGE_INSTALL += "\
	ubuntu-base \
	ubuntu-base-dev \
	ubuntu-base-dbg \
	ubuntu-base-doc \
"

# Without the kernel, modules, and firmware we can't really use the Linux
IMAGE_INSTALL += "\
	kernel-devicetree \
	kernel-image \
	${MACHINE_EXTRA_RRECOMMENDS} \
"

# We want to have an itb to boot from in the /boot directory to be flexible
# about U-Boot behavior
#IMAGE_INSTALL += "\
#   linux-kernelitb-norootfs-image \
#"
#####
IMAGE_FEATURES += " \
    dbg-pkgs \
    dev-pkgs \
    tools-sdk \
    debug-tweaks \
    tools-profile \
    package-management \
    splash \
    nfs-server \
    tools-debug \
    ssh-server-dropbear \
    tools-testapps \
    hwcodecs \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', \
       bb.utils.contains('DISTRO_FEATURES',     'x11', 'x11-base x11-sato', \
                                                       '', d), d)} \
"
ERPC_COMPS ?= ""
ERPC_COMPS_append_mx7ulp = "packagegroup-imx-erpc"

CORE_IMAGE_EXTRA_INSTALL += " \
    packagegroup-core-full-cmdline \
    packagegroup-tools-bluetooth \
    packagegroup-fsl-tools-audio \
    packagegroup-fsl-tools-gpu \
    packagegroup-fsl-tools-gpu-external \
    packagegroup-fsl-tools-testapps \
    packagegroup-fsl-tools-benchmark \
    packagegroup-fsl-gstreamer1.0 \
    packagegroup-fsl-gstreamer1.0-full \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11 wayland', 'weston-xwayland xterm', '', d)} \
    ${ERPC_COMPS} \
    "
#######

IMAGE_INSTALL += " \
    gstreamer1.0 \
    gstreamer1.0-plugins-base \
    gstreamer1.0-plugins-base                      \
    gstreamer1.0-plugins-base-adder                \
    gstreamer1.0-plugins-base-alsa                 \
    gstreamer1.0-plugins-base-app                  \
    gstreamer1.0-plugins-base-apps                 \
    gstreamer1.0-plugins-base-audioconvert         \
    gstreamer1.0-plugins-base-audiomixer           \
    gstreamer1.0-plugins-base-audiorate            \
    gstreamer1.0-plugins-base-audioresample        \
    gstreamer1.0-plugins-base-audiotestsrc         \
    gstreamer1.0-plugins-base-compositor           \
    gstreamer1.0-plugins-base-dbg                  \
    gstreamer1.0-plugins-base-dev                  \
    gstreamer1.0-plugins-base-doc                  \
    gstreamer1.0-plugins-base-encoding             \
    gstreamer1.0-plugins-base-gio                  \
    gstreamer1.0-plugins-base-locale-af            \
    gstreamer1.0-plugins-base-locale-az            \
    gstreamer1.0-plugins-base-locale-bg            \
    gstreamer1.0-plugins-base-locale-ca            \
    gstreamer1.0-plugins-base-locale-cs            \
    gstreamer1.0-plugins-base-locale-da            \
    gstreamer1.0-plugins-base-locale-de            \
    gstreamer1.0-plugins-base-locale-el            \
    gstreamer1.0-plugins-base-locale-en-gb         \
    gstreamer1.0-plugins-base-locale-eo            \
    gstreamer1.0-plugins-base-locale-es            \
    gstreamer1.0-plugins-base-locale-eu            \
    gstreamer1.0-plugins-base-locale-fi            \
    gstreamer1.0-plugins-base-locale-fr            \
    gstreamer1.0-plugins-base-locale-fur           \
    gstreamer1.0-plugins-base-locale-gl            \
    gstreamer1.0-plugins-base-locale-hr            \
    gstreamer1.0-plugins-base-locale-hu            \
    gstreamer1.0-plugins-base-locale-id            \
    gstreamer1.0-plugins-base-locale-it            \
    gstreamer1.0-plugins-base-locale-ja            \
    gstreamer1.0-plugins-base-locale-lt            \
    gstreamer1.0-plugins-base-locale-lv            \
    gstreamer1.0-plugins-base-locale-nb            \
    gstreamer1.0-plugins-base-locale-nl            \
    gstreamer1.0-plugins-base-locale-or            \
    gstreamer1.0-plugins-base-locale-pl            \
    gstreamer1.0-plugins-base-locale-pt-br         \
    gstreamer1.0-plugins-base-locale-ro            \
    gstreamer1.0-plugins-base-locale-ru            \
    gstreamer1.0-plugins-base-locale-sk            \
    gstreamer1.0-plugins-base-locale-sl            \
    gstreamer1.0-plugins-base-locale-sq            \
    gstreamer1.0-plugins-base-locale-sr            \
    gstreamer1.0-plugins-base-locale-sv            \
    gstreamer1.0-plugins-base-locale-tr            \
    gstreamer1.0-plugins-base-locale-uk            \
    gstreamer1.0-plugins-base-locale-vi            \
    gstreamer1.0-plugins-base-locale-zh-cn         \
    gstreamer1.0-plugins-base-meta                 \
    gstreamer1.0-plugins-base-ogg                  \
    gstreamer1.0-plugins-base-opengl               \
    gstreamer1.0-plugins-base-overlaycomposition   \
    gstreamer1.0-plugins-base-pango                \
    gstreamer1.0-plugins-base-pbtypes              \
    gstreamer1.0-plugins-base-playback             \
    gstreamer1.0-plugins-base-rawparse             \
    gstreamer1.0-plugins-base-src                  \
    gstreamer1.0-plugins-base-staticdev            \
    gstreamer1.0-plugins-base-subparse             \
    gstreamer1.0-plugins-base-tcp                  \
    gstreamer1.0-plugins-base-theora               \
    gstreamer1.0-plugins-base-typefindfunctions    \
    gstreamer1.0-plugins-base-videoconvert         \
    gstreamer1.0-plugins-base-videorate            \
    gstreamer1.0-plugins-base-videoscale           \
    gstreamer1.0-plugins-base-videotestsrc         \
    gstreamer1.0-plugins-base-volume               \
    gstreamer1.0-plugins-base-vorbis               \
    gstreamer1.0-plugins-base-ximagesink           \
    gstreamer1.0-plugins-base-xvimagesink          \
    libgstallocators-1.0                           \
    libgstapp-1.0                                  \
    libgstaudio-1.0                                \
    libgstfft-1.0                                  \
    libgstgl-1.0                                   \
    libgstpbutils-1.0                              \
    libgstriff-1.0                                 \
    libgstrtp-1.0                                  \
    libgstrtsp-1.0                                 \
    libgstsdp-1.0                                  \
    libgsttag-1.0                                  \
    libgstvideo-1.0                                \
    gstreamer1.0-plugins-good \
    gstreamer1.0-plugins-good                \
    gstreamer1.0-plugins-good-alaw           \
    gstreamer1.0-plugins-good-alpha          \
    gstreamer1.0-plugins-good-alphacolor     \
    gstreamer1.0-plugins-good-apetag         \
    gstreamer1.0-plugins-good-audiofx        \
    gstreamer1.0-plugins-good-audioparsers   \
    gstreamer1.0-plugins-good-auparse        \
    gstreamer1.0-plugins-good-autodetect     \
    gstreamer1.0-plugins-good-avi            \
    gstreamer1.0-plugins-good-cairo          \
    gstreamer1.0-plugins-good-cutter         \
    gstreamer1.0-plugins-good-dbg            \
    gstreamer1.0-plugins-good-debug          \
    gstreamer1.0-plugins-good-deinterlace    \
    gstreamer1.0-plugins-good-dev            \
    gstreamer1.0-plugins-good-dtmf           \
    gstreamer1.0-plugins-good-effectv        \
    gstreamer1.0-plugins-good-equalizer      \
    gstreamer1.0-plugins-good-flac           \
    gstreamer1.0-plugins-good-flv            \
    gstreamer1.0-plugins-good-flxdec         \
    gstreamer1.0-plugins-good-gdkpixbuf      \
    gstreamer1.0-plugins-good-goom           \
    gstreamer1.0-plugins-good-goom2k1        \
    gstreamer1.0-plugins-good-icydemux       \
    gstreamer1.0-plugins-good-id3demux       \
    gstreamer1.0-plugins-good-imagefreeze    \
    gstreamer1.0-plugins-good-interleave     \
    gstreamer1.0-plugins-good-isomp4         \
    gstreamer1.0-plugins-good-jpeg           \
    gstreamer1.0-plugins-good-lame           \
    gstreamer1.0-plugins-good-level          \
    gstreamer1.0-plugins-good-locale-af      \
    gstreamer1.0-plugins-good-locale-az      \
    gstreamer1.0-plugins-good-locale-bg      \
    gstreamer1.0-plugins-good-locale-ca      \
    gstreamer1.0-plugins-good-locale-cs      \
    gstreamer1.0-plugins-good-locale-da      \
    gstreamer1.0-plugins-good-locale-de      \
    gstreamer1.0-plugins-good-locale-el      \
    gstreamer1.0-plugins-good-locale-en-gb   \
    gstreamer1.0-plugins-good-locale-eo      \
    gstreamer1.0-plugins-good-locale-es      \
    gstreamer1.0-plugins-good-locale-eu      \
    gstreamer1.0-plugins-good-locale-fi      \
    gstreamer1.0-plugins-good-locale-fr      \
    gstreamer1.0-plugins-good-locale-fur     \
    gstreamer1.0-plugins-good-locale-gl      \
    gstreamer1.0-plugins-good-locale-hr      \
    gstreamer1.0-plugins-good-locale-hu      \
    gstreamer1.0-plugins-good-locale-id      \
    gstreamer1.0-plugins-good-locale-it      \
    gstreamer1.0-plugins-good-locale-ja      \
    gstreamer1.0-plugins-good-locale-ky      \
    gstreamer1.0-plugins-good-locale-lt      \
    gstreamer1.0-plugins-good-locale-lv      \
    gstreamer1.0-plugins-good-locale-mt      \
    gstreamer1.0-plugins-good-locale-nb      \
    gstreamer1.0-plugins-good-locale-nl      \
    gstreamer1.0-plugins-good-locale-or      \
    gstreamer1.0-plugins-good-locale-pl      \
    gstreamer1.0-plugins-good-locale-pt-br   \
    gstreamer1.0-plugins-good-locale-ro      \
    gstreamer1.0-plugins-good-locale-ru      \
    gstreamer1.0-plugins-good-locale-sk      \
    gstreamer1.0-plugins-good-locale-sl      \
    gstreamer1.0-plugins-good-locale-sq      \
    gstreamer1.0-plugins-good-locale-sr      \
    gstreamer1.0-plugins-good-locale-sv      \
    gstreamer1.0-plugins-good-locale-tr      \
    gstreamer1.0-plugins-good-locale-uk      \
    gstreamer1.0-plugins-good-locale-vi      \
    gstreamer1.0-plugins-good-locale-zh-cn   \
    gstreamer1.0-plugins-good-locale-zh-hk   \
    gstreamer1.0-plugins-good-locale-zh-tw   \
    gstreamer1.0-plugins-good-matroska       \
    gstreamer1.0-plugins-good-meta           \
    gstreamer1.0-plugins-good-mpg123         \
    gstreamer1.0-plugins-good-mulaw          \
    gstreamer1.0-plugins-good-multifile      \
    gstreamer1.0-plugins-good-multipart      \
    gstreamer1.0-plugins-good-navigationtest \
    gstreamer1.0-plugins-good-ossaudio       \
    gstreamer1.0-plugins-good-png            \
    gstreamer1.0-plugins-good-pulseaudio     \
    gstreamer1.0-plugins-good-replaygain     \
    gstreamer1.0-plugins-good-rtp            \
    gstreamer1.0-plugins-good-rtpmanager     \
    gstreamer1.0-plugins-good-rtsp           \
    gstreamer1.0-plugins-good-shapewipe      \
    gstreamer1.0-plugins-good-smpte          \
    gstreamer1.0-plugins-good-soup           \
    gstreamer1.0-plugins-good-spectrum       \
    gstreamer1.0-plugins-good-speex          \
    gstreamer1.0-plugins-good-src            \
    gstreamer1.0-plugins-good-staticdev      \
    gstreamer1.0-plugins-good-taglib         \
    gstreamer1.0-plugins-good-udp            \
    gstreamer1.0-plugins-good-video4linux2   \
    gstreamer1.0-plugins-good-videobox       \
    gstreamer1.0-plugins-good-videocrop      \
    gstreamer1.0-plugins-good-videofilter    \
    gstreamer1.0-plugins-good-videomixer     \
    gstreamer1.0-plugins-good-wavenc         \
    gstreamer1.0-plugins-good-wavparse       \
    gstreamer1.0-plugins-good-ximagesrc      \
    gstreamer1.0-plugins-good-y4menc         \
"

# GPU driver

IMAGE_INSTALL += " \
    imx-gpu-viv \
    libdrm-vivante \
    weston \
    weston-xwayland \
"

APTGET_EXTRA_PACKAGES_SERVICES_DISABLED += "\
	network-manager \
"
APTGET_EXTRA_PACKAGES += "\
	console-setup locales \
	mc htop \
\
	apt git vim \
	ethtool wget ftp iputils-ping lrzsz \
	net-tools \
	connman \
	openssh-server \
"
APTGET_EXTRA_SOURCE_PACKAGES += "\
	iproute2 \
"

# Add user navq with password navq and default shell bash
USER_SHELL_BASH = "/bin/bash"
USER_PASSWD_NAVQ = "\$5\$nEa6qsZxa\$YepRevGzGA375yrEUvZgoeXnFGEgfFrOrFeGyi.Gp09"
APTGET_ADD_USERS = "navq:${USER_PASSWD_NAVQ}:${USER_SHELL_BASH}"

HOST_NAME = "${MACHINE_ARCH}"

##############################################################################
# NOTE: We cannot install arbitrary Yocto packages as they will
# conflict with the content of the prebuilt Ubuntu rootfs and pull
# in dependencies that may break the rootfs.
# Any package addition needs to be carefully evaluated with respect
# to the final image that we build.
##############################################################################

# Minimum support for LS2 and S32V specific elements.
IMAGE_INSTALL_append_fsl-lsch3 += "\
    mc-utils-image \
    restool \
"

# We want easy installation of the BlueBox image to the target
DEPENDS_append_fsl-lsch3 = " \
    bbdeployscripts \
"

fakeroot do_update_host() {
	set -x

	echo >"${APTGET_CHROOT_DIR}/etc/hostname" "${HOST_NAME}"

	echo  >"${APTGET_CHROOT_DIR}/etc/hosts" "127.0.0.1 localhost"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "127.0.1.1 ${HOST_NAME}"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" ""
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "# The following lines are desirable for IPv6 capable hosts"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "::1 ip6-localhost ip6-loopback"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "fe00::0 ip6-localnet"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "ff00::0 ip6-mcastprefix"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "ff02::1 ip6-allnodes"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "ff02::2 ip6-allrouters"
	echo >>"${APTGET_CHROOT_DIR}/etc/hosts" "ff02::3 ip6-allhosts"

	set +x
}

fakeroot do_update_dns() {
	set -x

	if [ ! -L "${APTGET_CHROOT_DIR}/etc/resolv.conf" ]; then
		if [ -e "${APTGET_CHROOT_DIR}/etc/resolveconf" ]; then
			mkdir -p "/run/resolveconf"
			if [ -f "${APTGET_CHROOT_DIR}/etc/resolv.conf" ]; then
				mv -f "${APTGET_CHROOT_DIR}/etc/resolv.conf" "/run/resolveconf/resolv.conf"
			fi
			ln -sf  "/run/resolveconf/resolv.conf" "${APTGET_CHROOT_DIR}/etc/resolv.conf"
		elif [ -e "${APTGET_CHROOT_DIR}/etc/dhcp/dhclient-enter-hooks.d/resolved" ]; then
			mkdir -p "/run/systemd/resolve"
			if [ -f "${APTGET_CHROOT_DIR}/etc/resolv.conf" ]; then
				mv -f "${APTGET_CHROOT_DIR}/etc/resolv.conf" "/run/systemd/resolve/resolv.conf"
			fi
			ln -sf  "/run/systemd/resolve/resolv.conf" "${APTGET_CHROOT_DIR}/etc/resolv.conf"
		else
			touch "${APTGET_CHROOT_DIR}/etc/resolv.conf"
		fi
	fi

	set +x
}

fakeroot do_enable_network_manager() {
	set -x

	# In bionic, but not in xenial. We want all [network] interfaces to be managed
	# so that we do not have to mess with interface files individually
	if [ -e "${APTGET_CHROOT_DIR}/usr/lib/NetworkManager/conf.d/10-globally-managed-devices.conf" ]; then
		sed -i -E "s/^unmanaged-devices\=\*/unmanaged-devices\=none/g" "${APTGET_CHROOT_DIR}/usr/lib/NetworkManager/conf.d/10-globally-managed-devices.conf"
	fi

	set +x
}


IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE_append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

COMPATIBLE_MACHINE ="(.*ubuntu)"

FILESEXTRAPATHS_prepend := "${THISDIR}/u-boot-imx:"

SRC_URI += " \
    file://0001-Add-i.MX-8M-Mini-NavQ-board.patch \
    file://0002-Add-runtime-support-for-either-2-or-3-GB-RAM.patch \
    file://0003-Disable-SD3.0-since-it-s-not-working.patch \
"

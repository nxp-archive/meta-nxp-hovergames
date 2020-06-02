FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Add-NavQ-device-trees.patch \
    file://0001-Add-OV5645-driver.patch \
    file://0002-ARM64-dts-Add-OV5645-support-for-imx8mm-navq-board.patch \
"

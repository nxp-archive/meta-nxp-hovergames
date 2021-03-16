DESCRIPTION = "NXP Hovergames license install"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://EULA.txt \
    file://HoverGamesLicense.txt \ 
    file://SCR.txt \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}/opt/licenses
    install -m 0644 ${S}/EULA.txt ${D}/opt/licenses
    install -m 0644 ${S}/HoverGamesLicense.txt ${D}/opt/licenses
    install -m 0644 ${S}/SCR.txt ${D}/opt/licenses
}

FILES_${PN} = "/opt/licenses"

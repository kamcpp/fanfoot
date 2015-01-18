function getDocumentHeight() {
    return "innerHeight" in window
        ? window.innerHeight
        : document.documentElement.offsetHeight;
}
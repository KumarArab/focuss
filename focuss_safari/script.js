function blockReels() {
  if (window.location.href.startsWith("https://www.instagram.com/reels/")) {
    window.location.href = "https://www.google.com";
  }
  if (window.location.href.startsWith("https://www.youtube.com/shorts/")) {
    window.location.href = "https://www.google.com";
  }
}

blockReels();

document.addEventListener("DOMContentLoaded", blockReels);

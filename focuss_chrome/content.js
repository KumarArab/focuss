if (window.location.href.startsWith("https://www.instagram.com/reels/")) {
  window.location.href = "https://www.google.com";
}

const reelsObserver = new MutationObserver(() => {
  if (window.location.href.startsWith("https://www.instagram.com/reels/")) {
    window.location.href = "https://www.google.com";
  }
});

if (window.location.href.startsWith("https://www.youtube.com/shorts/")) {
  window.location.href = "https://www.google.com";
}

const shortsObserver = new MutationObserver(() => {
  if (window.location.href.startsWith("https://www.youtube.com/shorts/")) {
    window.location.href = "https://www.google.com";
  }
});

reelsObserver.observe(document, { subtree: true, childList: true });
shortsObserver.observe(document, { subtree: true, childList: true });
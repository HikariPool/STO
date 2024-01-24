$('#logOutItem').click(logout);
$('#chatsItem').click(() => navigateIframe('/frame/chats_frame.html'));

let iframe = document.getElementById('workspace');
window.addEventListener('message', (path) => navigateIframe(path.data));
window.addEventListener('onbeforeunload ', () => {
    clearIntervals();
});

function logout() {
    $.ajax({
        url: '/auth/logout',
        type: 'POST',
        success: () => location.reload()
    });
}

function navigateIframe(path) {
    iframe.style = 'visibility: hidden';
    iframe.src = path;
}
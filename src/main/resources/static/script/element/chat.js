let openChatButton = document.getElementById('openChatButton');
let chatLayout = document.getElementById('chat');
let messageContainer = document.getElementById('messageContainer');
let messageButton = document.getElementById('messageButton');
let messageField = document.getElementById('messageField');

let isChatOpen = false;
let reloadChatIntervalId;


openChatButton.addEventListener('click', () => {
    if (isChatOpen) {
        clearInterval(reloadChatIntervalId);
        chatLayout.style = 'display: none';
        isChatOpen = false;
    } else {
        reloadChat();
        reloadChatIntervalId = setInterval(reloadChat, 2000);
        chatLayout.style = 'display: block';
        isChatOpen = true;
    }
});

messageButton.addEventListener('click', () => {
    $.ajax({
        url: '/chat/create?session_id=' + workspaceHead.getAttribute('session_id'),
        type: 'POST',
        data: {
            text: messageField.value
        },
        success: () => {
            messageField.value = '';
            reloadChat();
        }
    });
});


function reloadChat() {

    $.ajax({
        url: '/chat/get?session_id=' + workspaceHead.getAttribute('session_id'),
        type: 'GET',
        success: (data) => showMessages(data)
    });
}

function showMessages(messages) {
    messageContainer.innerHTML = '';

    for (let i = 0; i < messages.length; i++) {
        let div = document.createElement('div');
        div.className = 'messageItem';

        let title = document.createElement('h3');
        title.className = 'lineListItemTitle messageAuthor';
        title.textContent = messages[i].createdBy.username;

        let text = document.createElement('h4');
        text.className = 'message';
        text.textContent = messages[i].text;


        div.append(title, text);
        messageContainer.append(div);
    }
}
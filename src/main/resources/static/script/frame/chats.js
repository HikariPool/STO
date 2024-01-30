let itemContainer = document.getElementById('listItemContainer');

reloadContent();

function reloadContent() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/chat/getChats',
        type: 'GET',
        success: data => showContentItems(data)
    });
}

function showContentItems(chats) {
    for (let i = 0; i < chats.length; i++) {
        let div = document.createElement('div');
        div.className = 'lineListItem parentElement';
        div.id = chats[i].id;
        div.setAttribute('type', 'contentItem');

        let h3 = document.createElement('h3');
        h3.className = 'lineListItemTitle';
        h3.textContent = chats[i].createdBy.username + ' Message[' + chats[i].messages[0].text + ']';

        div.appendChild(h3);
        itemContainer.appendChild(div);

//        div.onclick = () =>
//            window.parent.playContent(
//                '/stream/get/' + chats[i].sourcePath, undefined, player => window.top.sync(player, chats.id), chats[i].previewPath);
    }
}

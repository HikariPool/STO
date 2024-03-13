let itemContainer = document.getElementById('listItemContainer');
let iframe = document.getElementById('workspace');

reloadContent();

function reloadContent() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/chat/all',
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
        h3.textContent = chats[i].createdBy.username + ' : ' + chats[i].messages[0].text;

        div.appendChild(h3);
        itemContainer.appendChild(div);
        div.onclick = () => {
           window.parent.postMessage('/frame/car_service_frame.html?chat_id=' + chats[i].id );
        }
    }
}

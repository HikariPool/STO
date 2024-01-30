let itemContainer = document.getElementById('listItemContainer');

reloadContent();

function reloadContent() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/session/get?session_id=' + getParam('session_id'),
        type: 'GET',
        success: data => showContentItems(data)
    });
}

function showContentItems(session) {
    workspaceHead.setAttribute('session_id', session.id);

    title.html(session.title);

    let contentItems = session.contentItems;
    for (let i = 0; i < contentItems.length; i++) {
        let div = document.createElement('div');
        div.className = 'lineListItem parentElement';
        div.id = contentItems[i].id;
        div.setAttribute('type', 'contentItem');


        let img = document.createElement('img');
        img.className = 'lineListItemImage';
        img.src = contentItems[i].previewPath;

        let h3 = document.createElement('h3');
        h3.className = 'lineListItemTitle';
        h3.textContent = contentItems[i].title;

        div.appendChild(img);
        div.appendChild(h3);
        itemContainer.appendChild(div);

        div.onclick = () =>
            window.parent.playContent(
                '/stream/get/' + contentItems[i].sourcePath, undefined, player => window.top.sync(player, session.id), contentItems[i].previewPath);
    }
}

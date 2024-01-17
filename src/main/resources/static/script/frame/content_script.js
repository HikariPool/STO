let popup = document.getElementById('popup');
let addButton = document.getElementById('addButton');
let titleField = $('#titleField');
let title = $('#title');
let itemContainer = document.getElementById('listItemContainer');
let editButton = document.getElementById('editButton');
let editMenu = document.getElementById('editMenu');
let workspaceHead = document.getElementById('workspaceHead');


$('#upload').click(openFileChooser);
$('#createButton').click(addContentItem);


reloadContent();


let files;


addButton.addEventListener('click', () => {
    showAndHighOnClickOut(popup);
});

function openFileChooser() {
    var input = document.createElement('input');
    input.setAttribute('multiple', 'true');
    input.type = 'file';

    input.onchange = e => files = e.target.files;
    input.click();
}

function addContentItem() {
    let formData = new FormData;
    let img;
    let media;

    for (let file of files) {
        if (file !== undefined && file != undefined && file !== undefined &&
            (file.name.indexOf('.jpg') !== -1 || file.name.indexOf('.png') !== -1)) {
            img = file;
        }

        if (file !== undefined && file != undefined && file !== undefined &&
            (file.name.indexOf('.mp3') !== -1 || file.name.indexOf('.mp4') !== -1 || file.name.indexOf('.mov') !== -1)) {
            media = file;
        }
    }

    formData.append('img', img);
    formData.append('media', media);
    formData.append('title', titleField.val());

    $.ajax({
        url: '/session/content/create?session_id=' + getParam('session_id'),
        contentType: false,
        processData: false,
        data: formData,
        type: 'POST',
        success: () => {
            closePopup();
            reloadContent();
        },
        error: xhr => showError(JSON.parse(xhr.responseText).message)
    });
}

function closePopup() {
    popup.style.display = 'none';
    titleField.val(null);
    hideErrorMessage();
    files = null;
}


function reloadContent() {
    itemContainer.innerHTML = '';

    $.ajax({
        url: '/session/get?session_id=' + getParam('session_id'),
        type: 'GET',
        success: data => showContentItems(data)
    });
}

function getParam(title) {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get(title);
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

editButton.addEventListener('click', () => {
    showAndHighOnClickOut(editMenu);
    editMenu.addEventListener('click', () => editMenu.style.display = 'none');
});

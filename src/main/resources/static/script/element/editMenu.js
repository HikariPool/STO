let deleteItem = document.getElementById('deleteItem');
let infoField = document.getElementById('infoField');
let addPeopleItem = document.getElementById('addPeopleItem');
let addPeopleField = document.getElementById('addPeopleField');
let contentModalView = document.getElementById('contentModalView');
let addPeopleButton = document.getElementById('addPeopleButton');

infoField.addEventListener('click', () => {
    infoField.style.display = 'none';
    reloadContent();
});

deleteItem.addEventListener('click', () => {
    infoField.style.display = 'block';

    for (let item of itemContainer.children) {
        item.addEventListener('click', () => {
            removeItem(item.id, item.getAttribute('type'));
            infoField.style.display = 'none';
        });
    }
});

function removeItem(id, type) {
    $.ajax({
        url: '/generic/remove?type=' + type + '&id=' + id,
        type: 'POST',
        success: reloadContent
    });
}

addPeopleItem.addEventListener('click', () => {
    showAndHighOnClickOut(contentModalView);
});


addPeopleButton.addEventListener('click', () => {
    $.ajax({
        url: '/session/people?session_id=' + workspaceHead.getAttribute('session_id') + '&email=' + addPeopleField.value,
        type: 'POST',
        success: () => addPeopleField.value = ''
    });
});



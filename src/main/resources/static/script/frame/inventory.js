let addButton = document.getElementById('addButton')
let editButton = document.getElementById('editButton')
let listItemContainer = document.getElementById('listItemContainer')

let inventoryItemTemplate
let paramItem

loadTemplates()

addButton.addEventListener('click', () => {
      inventoryItem = createElementFromTemplate(inventoryItemTemplate)
      paramItem = createElementFromTemplate(paramItem)
      tbody = inventoryItem.getElementsByTagName('tbody')[0]

      tbody.append(paramItem)
      listItemContainer.append(inventoryItem)
});

function createElementFromTemplate(html){
      const template = document.createElement('template');
      template.innerHTML = html;
      return template.content.children[0];
}
function loadTemplates(){
    $.ajax({
        url: '/frame/inventoryItem.html',
        type: 'GET',
        success: (data) => {
            inventoryItemTemplate = data
        }
    });

    $.ajax({
        url: '/frame/paramItem.html',
        type: 'GET',
        success: (data) => {
            paramItem = data
        }
    });
}
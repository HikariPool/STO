let addButton = document.getElementById('addButton')
let editButton = document.getElementById('editButton')
let listItemContainer = document.getElementById('listItemContainer')

let inventoryItemTemplate
let paramItem
const reader = new FileReader();

loadTemplates()

addButton.addEventListener('click', () => {
      inventoryItem = createElementFromTemplate(inventoryItemTemplate)
      tbody = inventoryItem.getElementsByTagName('tbody')[0]
      let img = []
      var imgName = {str:''}

      inventoryItem.querySelectorAll('[name="upload"]')[0].addEventListener('click', () => {
          openFileChooser(img,imgName)
      })
      inventoryItem.querySelectorAll('[name="add"]')[0].addEventListener('click', () => {
          tbody.append(createElementFromTemplate(paramItem))
      })
      inventoryItem.querySelectorAll('[name="save"]')[0].addEventListener('click', () => {
            saveParams(inventoryItem, img, imgName)
      })

      listItemContainer.append(inventoryItem)
});

function saveParams(inventoryItem, img, imgName){
    var dto = new Map

    dto.set('img', img)
    dto.set('imageName', imgName.str)

    var names = inventoryItem.querySelectorAll('[name="name"]')
    var values = inventoryItem.querySelectorAll('[name="value"]')

    for(var i = 0; i < names.length; i++){
        dto.set(names[i].value, values[i].value)
    }
    if(names.length != 0){
        send(dto)
    }
}

function send(dto){
    $.ajax({
            url: '/inventory/create',
            type: 'POST',
            contentType: false,
            processData: false,
            data: JSON.stringify(Object.fromEntries(dto)),
            success: (data) => {

            }
        });
}

function openFileChooser(img, imgName) {
    var input = document.createElement('input');
    input.type = 'file';

    input.onchange = function (e){
     imgName.str = e.target.files[0].name

        reader.readAsArrayBuffer(e.target.files[0]);
          reader.onloadend = (evt) => {
            if (evt.target.readyState === FileReader.DONE) {
              const arrayBuffer = evt.target.result,
                array = new Uint8Array(arrayBuffer);
              for (const a of array) {
                img.push(a);
              }
          }
        }
    }
    input.click();
}

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
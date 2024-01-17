let lastX;
let lastY;
let predCoordinateIsExists = false;

if (!isMobile()) {
    const liquid_array = document.getElementsByClassName('liquid');
    let window = getWindowCoordinate();


    for (let i = 0; i < liquid_array.length; i++) {
        let liquid = liquid_array.item(i);
        liquid.style.animationPlayState = 'running';

        setMouseMoveLiquidEvent(liquid);
        setReturningInVisibleZoneListener(liquid, window);
        clearPredCoordinateWhenMouseOut(liquid);
    }
}


function isMobile() {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
}

function setMouseMoveLiquidEvent(liquid) {
    liquid.addEventListener('mousemove', function (e) {
            if (predCoordinateIsExists) {
                let newX = e.clientX * (e.clientX - lastX) / 15;
                let newY = e.clientY * (e.clientY - lastY) / 15;

                liquid.style.transform = 'translate(' + newX + 'px, ' + newY + 'px)'
                    + ' rotate(' + rotateDirection(newX) + 'deg)';
            }
            lastX = e.clientX;
            lastY = e.clientY;
            predCoordinateIsExists = true;
        }
    );
}

function rotateDirection(newX) {
    return 0.3 * (newX - lastX);
}

function setReturningInVisibleZoneListener(liquid, window) {
    liquid.addEventListener('animationiteration', function () {
        let element = getElementCoordinate(liquid);

        if (element.left < window.left) {
            liquid.style.transform = 'translate(200px)';
        }

        if (element.right > window.right) {
            liquid.style.transform = 'translate(-200px)';
        }

        if (element.top > window.top) {
            liquid.style.transform = 'translate(0px -200px)';
        }

        if (element.bottom > window.bottom) {
            liquid.style.transform = 'translate(0px 200px)';
        }
    });
}

function getElementCoordinate(element) {
    return {
        top: window.pageYOffset + element.getBoundingClientRect().top,
        left: window.pageXOffset + element.getBoundingClientRect().left,
        right: window.pageXOffset + element.getBoundingClientRect().right,
        bottom: window.pageYOffset + element.getBoundingClientRect().bottom
    }
}

function getWindowCoordinate() {
    return {
        top: window.pageYOffset,
        left: window.pageXOffset,
        right: window.pageXOffset + document.documentElement.clientWidth,
        bottom: window.pageYOffset + document.documentElement.clientHeight
    };
}

function clearPredCoordinateWhenMouseOut(liquid) {
    liquid.addEventListener('mouseout', function () {
        predCoordinateIsExists = false;
    });
}

let playButton = document.getElementById('playTrack');
let nextButton = document.getElementById('nextTrack');
let prevButton = document.getElementById('prevTrack');
let progressBar = document.getElementById('progressIndicator');
let trackIcon = document.getElementById('trackIcon');
let musicPlayerWrapper = document.getElementById('musicPlayerWrapper');

let audio = new Audio();

audio.onplay = () => musicPlayerWrapper.style = "display: block";

function playAudio(path, onEnd, onUpdate, imagePath) {
    if (imagePath !== null) {
        trackIcon.src = imagePath;
    }
    if (path !== null) {
        audio.src = path;
    }
    audio.play();
    //todo change play icon
    //todo change preview playing track
    audio.ontimeupdate = event => {
        updateProgressBar(event.target);
        if (onUpdate !== undefined) {
            onUpdate(event.target);
        }
    };
    audio.onended = () => {
        //todo change play icon
        if (onEnd !== undefined) {
            onEnd();
        }
    };
    playButton.onclick = () => stopAudio(audio, onEnd, onUpdate);
}

function stopAudio(audio, onEnd, onUpdate) {
    audio.ontimeupdate = null;
    audio.pause();
    playButton.onclick = () => playAudio(null, onEnd, onUpdate, null);
}

function updateProgressBar(audio) {
    let percent = audio.currentTime * 100 / audio.duration;
    progressBar.style = 'width: ' + percent + '%';
}

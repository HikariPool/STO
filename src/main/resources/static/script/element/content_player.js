let playButton = document.getElementById('playTrack');
let nextButton = document.getElementById('nextTrack');
let prevButton = document.getElementById('prevTrack');
let progressBar = document.getElementById('progressIndicator');
let trackIcon = document.getElementById('trackIcon');
let musicPlayerWrapper = document.getElementById('musicPlayerWrapper');
let videoWrapper = document.getElementById('videoWrapper');

let video = document.getElementById('videoPlayer');
let audio = new Audio();


function playContent(path, onEnd, onUpdate, imagePath, currentTime) {
    stillSyncPlay = true;
    let player;

    if (path !== undefined && path.includes('.mp3')) {
        player = audio;
        showAndHighOnClickOut(musicPlayerWrapper, e => stopPlay(audio));
        playButton.onclick = () => stopPlay(player, onEnd, onUpdate);
    } else if (path !== undefined && (path.includes('.mp4')
        || path.includes('.mov'))) {
        player = video;
        video.onpause = () => stopPlay(player, onEnd, onUpdate);
        showAndHighOnClickOut(videoWrapper, e => stopPlay(video));
    }


    if (imagePath !== null) {
        trackIcon.src = imagePath;
    }
    if (path !== null) {
        player.src = path;
    }
    if (currentTime !== undefined && currentTime > 0.0) {
        player.currentTime = currentTime;
    }
    player.play();

    player.ontimeupdate = event => {
        updateProgressBar(event.target);
        if (onUpdate !== undefined) {
            onUpdate(event.target);
        }
    };
    player.onended = () => {
        //todo change play icon
        if (onEnd !== undefined) {
            onEnd();
        }
    };
}

function stopPlay(player, onEnd, onUpdate) {
    stillSyncPlay = false;
    player.pause();
    player.ontimeupdate = null;
    if (player.src !== undefined && player.src.includes('.mp3')) {
        playButton.onclick = () => playContent(player.src, onEnd, onUpdate, trackIcon.src, player.currentTime);
    }
}

function updateProgressBar(audio) {
    let percent = audio.currentTime * 100 / audio.duration;
    progressBar.style = 'width: ' + percent + '%';
}

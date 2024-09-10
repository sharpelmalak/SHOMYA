self.onmessage = function (event) {
    var { type, value } = event.data;

    fetch('/shomya/check-unique?' + type + '=' + encodeURIComponent(value))
        .then(response => response.json())
        .then(data => {
            self.postMessage({ type, data });
        });
};

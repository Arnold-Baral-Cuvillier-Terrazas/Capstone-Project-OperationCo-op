const url = 'http://localhost:8010/proxy/v4/games/';
const options = {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Authorization' : 'Bearer ' + BEARER,
        'Client-ID' : CLIENT_ID,

    },
    body : "fields name; sort rating desc; where first_release_date > 1577836800; "
};
fetch(url, options)
    .then(function (data){
       data.json().then(function(json){
           console.log(json)
           for(let game of json){
               console.log(`INSERT INTO games(title, art_cover, critics_rating, description) VALUES (${game.name},${game.cover},${game.total_rating},${game.summary})`)
           }
       })
    })
    .catch();
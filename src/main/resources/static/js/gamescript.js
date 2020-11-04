const get_games = (query) => {
    return fetch(API_URL + '/games', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Client-ID': CLIENT_ID,
            'Authorization': `Bearer ${BEARER}`,
        },
        body: query,
    })
}
const get_cover = (id) => {
    return fetch(API_URL + '/covers', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Client-ID': CLIENT_ID,
            'Authorization': `Bearer ${BEARER}`,
        },
        body: `fields id, url; where id=${id};`
    })
}
(async function () {
    let games = [];
    let cover_urls = [];
    get_games('fields name, first_release_date, summary, total_rating; where total_rating > 50; limit 104;')
        .then(response => {
            response.json().then(function (json) {
                games = json.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0))
                console.log(games);

                })
            })
        // })
        .then( function() {
            // this is where we want to go through 1 at a time and get the covers for each of the games
            // console.log("IDs for each of the games: " + ids);
        })
        .catch(err => console.error(err));
})();
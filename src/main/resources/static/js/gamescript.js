// // const url = 'http://localhost:8010/proxy/v4/games';//need to change this back to original api url
// // const options = {
// //     method: 'POST',
// //     headers: {
// //         'Content-Type': 'application/json',
// //         'Authorization' : 'Bearer ' + BEARER,
// //         'Client-ID' : CLIENT_ID,
// //
// //     },
// //     body : "fields *; where first_release_date > 1577836800 & multiplayer_modes != null; limit 100;"
// //
// // };
// // fetch(url, options)
// //     .then(function (data){
// //        data.json().then(function(json){
// //            console.log(json)
// //            for(let game of json){
// //                console.log(`INSERT INTO games(title, art_cover, critics_rating, description) VALUES ('${game.name}','${game.cover}','${game.total_rating}','${game.summary}')`)
// //            }
// //        })
// //     })
// //     .catch();
//
// const get_games = (query) => {
//     return fetch(API_URL + '/games', {
//         method: 'POST',
//         headers: {
//             'Accept': 'application/json',
//             'Client-ID': CLIENT_ID,
//             'Authorization': `Bearer ${BEARER}`,
//         },
//         body: query
//     })
// }
// const get_covers = (ids) => {
//     return fetch(API_URL + '/covers', {
//         method: 'POST',
//         headers: {
//             'Accept': 'application/json',
//             'Client-ID': CLIENT_ID,
//             'Authorization': `Bearer ${BEARER}`,
//         },
//         body: `fields id, url; limit 104;`
//     })
// }
//
// (async function () {
//     let games = [];
//     let cover_urls = [];
//     get_games('fields name, first_release_date, summary, total_rating; where total_rating > 50; limit 104;')
//         .then(response => {
//             response.json().then(function (json) {
//                 games = json.sort((a, b) => (a.id > b.id) ? 1 : ((b.id > a.id) ? -1 : 0))
//                 console.log(games);
//
//                 if (games.length > 0) {
//                     // Game IDs to CSV
//                     let ids = games
//                         .reduce((a, v) => {
//                             if(v.cover){
//                             a.push(v.cover);
//                             }
//                             return a;
//                         }, []).join(',');
//                     console.log(ids);
//                     // Pass to covers endpoint
//                     get_covers(ids)
//                         .then(response => {
//                             response.json().then(function (json) {
//                                 // cover_urls = json.sort((a, b) => (a.game > b.game) ? 1 : ((b.game > a.game) ? -1 : 0))
//                                 //     .reduce((a, v) => {
//                                 //         a.push(v.url.substring(2));
//                                 //         return a;
//                                 //     }, []);
//                                 // console.log(cover_urls);
//                                 console.log(json);
//                             });
//                         })
//                         .catch(err => console.error(err));
//                 }
//             });
//         })
//         .catch(err => console.error(err));
//
//
// })();

// const url = 'http://localhost:8010/proxy/v4/games';//need to change this back to original api url
// const options = {
//     method: 'POST',
//     headers: {
//         'Content-Type': 'application/json',
//         'Authorization' : 'Bearer ' + BEARER,
//         'Client-ID' : CLIENT_ID,
//
//     },
//     body : "fields *; where first_release_date > 1577836800 & multiplayer_modes != null; limit 100;"
//
// };
// fetch(url, options)
//     .then(function (data){
//        data.json().then(function(json){
//            console.log(json)
//            for(let game of json){
//                console.log(`INSERT INTO games(title, art_cover, critics_rating, description) VALUES ('${game.name}','${game.cover}','${game.total_rating}','${game.summary}')`)
//            }
//        })
//     })
//     .catch();
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
                // so now 'games' is sorted into it's json object
                // grab the game cover based on each id
                // games.forEach(function(thisGame) {
                //     thisGame.cover = get_cover(thisGame.id)
                //         .then(response => {
                //             response.json().then(function (json) {
                //                 return json.url;
                //             })
                //                 .catch(err => console.error(err));
                //         })
                // });
                if (games.length > 0) {
                    // Game IDs to CSV
                    // let ids = games
                    //     .reduce((a, v) => {
                    //         if(v.cover){
                    //         a.push(v.cover);
                    //         }
                    //         return a;
                    //     }, []).join(',');
                    // console.log(ids);
                    // Pass to covers endpoint
                    // get_covers(ids)
                    //     .then(response => {
                    //         response.json().then(function (json) {
                    //             // cover_urls = json.sort((a, b) => (a.game > b.game) ? 1 : ((b.game > a.game) ? -1 : 0))
                    //             //     .reduce((a, v) => {
                    //             //         a.push(v.url.substring(2));
                    //             //         return a;
                    //             //     }, []);
                    //             // console.log(cover_urls);
                    //             console.log(json);
                    //         });
                    //     })
                    //     .catch(err => console.error(err));
                }
            });
        })
        .then( function() {
            // this is where we want to go through 1 at a time and get the covers for each of the games
            // console.log("IDs for each of the games: " + ids);
        })
        .catch(err => console.error(err));
})();

client.test("Request executed successfully", function () {
    client.assert(response.status === 200, "Response status is not 200")
    client.assert(typeof response.body.access_token !== "undefined", "Response don't have token")
})

client.global.set("access_token", response.body.access_token)
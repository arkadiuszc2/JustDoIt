import axios from 'axios'

export const backendApi = (url) => {
  const urlForDev = 'http://localhost:8080' //temporal url for development and testing

  const client = axios.create({
    baseURL: urlForDev + url,
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json'
    }
  })

  client.interceptors.response.use(response => {
    return response
  }, function (error) {
    console.log('Error: An error occurred while calling backend', error)
    console.log(error.response) //for testing
    if (error.response) {
      console.log("if 1") //for testing
      // client received an error response (5xx, 4xx)
      if (error.response.status === 404) {
        console.log("if 2") //fort testing
        return { status: error.response.status }
      }
      return Promise.reject(error.response)
    } else if (error.request) {
      console.log("if 3") //for testing
      // client never received a response (we can set custom timeout for axios request), 
      // or request never left
      return Promise.reject("Error: No response from the server")
    } else {
      console.log("if 4") //for testing
      // anything else
    }
  })

  return client
}
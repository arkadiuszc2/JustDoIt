import { useState, useEffect } from "react";

const useFetch = (apiMethod, id) => {
    const [data, setData] = useState(null);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null)

    // note - on strict mode react renders components twice, so it will run 2 times
    useEffect(() => {
        setTimeout(() => {              //timeout for testing only
            apiMethod(id)
                .then(response => {
                    if (response.status !== 200) {
                        console.log(response.status);
                        throw Error("Error: could not fetch data");
                    }
                    return response;
                })
                .then((data) => {
                    setData(data)
                    setIsPending(false);
                    setError(null);
                    console.log("Fetching done");
                })
                .catch(error => {
                    setError(error);
                    setIsPending(false);
                })
        }, 1000);
    }, [apiMethod]);
    return {data, isPending, error}
}

export default useFetch;
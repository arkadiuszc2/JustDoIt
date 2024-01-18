import { useState, useEffect } from "react";

const useFetch = (apiMethod) => {
    const [data, setData] = useState(null);
    const [isPending, setIsPending] = useState(true);
    const [error, setError] = useState(null)

    // note - on strict mode react renders components twice, so it will run 2 times
    useEffect(() => {
        setTimeout(() => {              //timeout for testing only
            apiMethod()
                .then(response => {
                    if (!response.ok) {
                        throw Error("Error: could not fetch data");
                    }
                    return response;
                })
                .then((data) => {
                    setData(data)
                    setIsPending(false);
                    setError(null);
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
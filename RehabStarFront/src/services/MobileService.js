export const getUserInfo = (name) => {
    //let username = name.toLowerCase().trim();
    const URL = `http://10.0.2.2:8080/findUserByUserName/${name}`;
    return fetch(URL).then((response) => response.json());
}

export const getAllUsers = () => {
    const URL = `http://10.0.2.2:8080/findAllUsers`;
    return fetch(URL);
}

export const getAllStories = () => {
    const URL = `http://10.0.2.2:8080/findAllStories`;
    return fetch(URL).then((response) => response.json());
}

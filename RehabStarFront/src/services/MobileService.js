export const getUserInfo = (name) => {
    let username = name.toLowerCase().trim();
    const URL = `http://127.0.0.1:8081/findUserByUserName/${username}`;
    return fetch(URL);

}

export const getAllUsers = () => {
    const URL = `http://127.0.0.1:8081/findAllUsers`;
    return fetch(URL);

}

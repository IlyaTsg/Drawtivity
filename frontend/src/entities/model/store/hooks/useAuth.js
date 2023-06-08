import {useSelector} from "react-redux";

const useAuth = () =>{
    const {email, token, id} = useSelector(state => state.user)
    return {
        isAuth: !!id,
        email,
        token,
        id
    }
}

export default useAuth;
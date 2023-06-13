import {useSelector} from "react-redux";

const useImage = () =>{
    return useSelector(state=>state.task.image)
}

export default useImage;
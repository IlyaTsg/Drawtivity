import { useSelector } from 'react-redux'

const useAuth = () => {
  const { email, token, id } = useSelector(state => state.user)

  console.log('render auth')
  //email, id = null
  return {
    isAuth: !!localStorage.key('token'),
    email,
    token,
    id,
  }
}

export default useAuth
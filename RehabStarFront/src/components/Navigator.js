import { StackNavigator } from 'react-navigation';
import Login from './Login/Login';
import ProfileScreen from './ProfileScreen';
import MethodTest from './MethodTest'


const Navigator = StackNavigator ({

        Home: {screen: Login},
        Profile: {screen: ProfileScreen},
        Test: {screen: MethodTest},
    },
    {
        initialRouteName: 'Home',
        headerMode: 'null',
    });

export default Navigator;

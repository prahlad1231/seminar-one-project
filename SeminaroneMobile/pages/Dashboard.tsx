import {Text, TouchableOpacity} from 'react-native';
import React from 'react';
import {SafeAreaView} from 'react-native-safe-area-context';

const Dashboard = ({navigation}) => {
  return (
    <SafeAreaView>
      <Text className="font-bold">Dashboard</Text>
      <TouchableOpacity onPress={() => navigation.navigate('signup')}>
        <Text>Signup</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => navigation.navigate('login')}>
        <Text>Login</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

export default Dashboard;

import {Box, Tab, Tabs} from '@mui/material';
import {useState} from 'react';

function a11yProps(index) {
  return {
    id: `vertical-tab-${index}`, 'aria-controls': `vertical-tabpanel-${index}`,
  };
}

const Menu = ({value, setValue}) => {

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (

    <Box sx={{width: '100%', borderRight: 1, borderColor: 'divider'}}>
      <Tabs
        orientation="vertical"
        value={value}
        onChange={handleChange}
        aria-label="Vertical tabs example"
        sx={{
          backgroundColor: 'white', position: 'sticky', top: 90,
        }}
        textColor="black"
        indicatorColor="primary"
        TabIndicatorProps={{fontWeight: 'bolder'}}
      >
        <Tab label="Решение задач" {...a11yProps(0)}
             sx={{
               fontSize: 16,
               color: value === 0 ? 'black' : 'grey',
               fontFamily: 'Roboto, Arial, sans-serif',
             }}
        />
        <Tab label="Создание задач" {...a11yProps(1)}
             sx={{
               fontSize: 16,
               color: value === 1 ? 'black' : 'grey',
             }}
        />
        <Tab label="Интеграция" {...a11yProps(2)}
             sx={{
               fontSize: 16,
               color: value === 2 ? 'black' : 'grey',
             }}
        />
      </Tabs>
    </Box>);
};

export default Menu;
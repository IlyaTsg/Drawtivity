import React from 'react';
import { Box } from '@mui/material';
import MainLtiWidget from '../../../../widgets/lti/ui/MainLtiWidget/MainLtiWidget';

const LtiSolution = () => {

  return (
    <Box width={'100vw'} height={'100%'} display={'flex'} justifyContent={'center'} alignItems={'center'} mt={8}>
      <MainLtiWidget />
    </Box>
  );
};

export default LtiSolution;
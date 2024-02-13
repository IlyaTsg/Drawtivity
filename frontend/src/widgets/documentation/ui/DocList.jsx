import DocCard from '../../../entities/documentation/ui/DocCard';
import {Box, Typography} from '@mui/material';


const DocList = ({title, arr}) => {

  return (
    <Box>
      <Typography variant="h4" ml={10} mt={3} mb={2} width={'1240px'}
                  borderBottom={'2px solid black'}>{title}</Typography>
      <Box display={'flex'} flexWrap={'wrap'} rowGap={10} columnGap={10} ml={10} mb={3}>
        {arr.map((item) =>
          <DocCard
            key={item.title}
            props={{title: item.title, text: item.text}}
          />,
        )}
      </Box>
    </Box>
  );
};

export default DocList;
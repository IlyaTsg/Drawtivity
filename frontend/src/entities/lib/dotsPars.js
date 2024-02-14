export const dotsPars = (str) => {
  const dotsArr = str.split(' ');
  const dotsObjArr = [];
  dotsArr.map(item => {
    dotsObjArr.push(
      {
        x: +item.split(':')[0],
        y: +item.split(':')[1],
      });
  });
  return dotsObjArr;
};
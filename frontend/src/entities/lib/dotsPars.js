export const dotsPars = (str) => {

  const dotsArr = str.split(' ');
  const dotsObjArr = [];
  dotsArr.map(item => dotsObjArr.push(
    {
      x: Number(item.split(':')[0]),
      y: Number(item.split(':')[1]),
    }));
  return dotsArr;
};
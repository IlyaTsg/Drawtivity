import {useMemo} from 'react';

export const useFiltered = (tasks, typeSt, category, inpState) => {
  const filteredByType = useMemo(() => {
    if (!typeSt)
      return tasks;
    return tasks.filter(item => item.type === typeSt.toString());
  }, [typeSt, tasks]);

  const filteredByCategoryAndType = useMemo(() => {
    if (!category)
      return filteredByType;
    return filteredByType.filter(item => item.category === category);
  }, [category, filteredByType]);

  return useMemo(() => {
    if (!inpState)
      return filteredByCategoryAndType;
    return filteredByCategoryAndType.filter(item => item.title.includes(inpState));
  }, [inpState, filteredByCategoryAndType]);
};
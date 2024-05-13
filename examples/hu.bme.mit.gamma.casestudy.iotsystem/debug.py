from time import time
import sys
sys.path.append('simulator-gen/.')
from pyro.infer.inspect import get_dependencies

from conditionalserviceavailability_simulator import *
import json
def default_json(t):
    return f'{list(t)}'
print(json.dumps(get_dependencies(simulate), indent=1, default=default_json))
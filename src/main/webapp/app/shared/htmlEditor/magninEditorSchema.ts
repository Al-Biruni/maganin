// import {nodes as basicNodes} from "ngx-editor/schema/public_api";
import { nodes as basicNodes, marks } from 'ngx-editor';
import { Schema, NodeSpec, DOMOutputSpec } from 'prosemirror-model';
const codeMirror: NodeSpec = {
  content: 'text*',
  attrs: { type: { default: 'p' } },
  marks: '_',
  group: 'block',
  code: false,
  defining: true,
  isolating: false,
  parseDOM: [
    {
      tag: 'pre',
      preserveWhitespace: 'full',
    },
  ],
  toDOM(): DOMOutputSpec {
    return ['pre', ['div', 0]];
  },
};

const nodes = Object.assign({}, basicNodes, {
  codemirror: codeMirror,
});

const schema = new Schema({
  nodes,
  marks,
});
export default schema;
